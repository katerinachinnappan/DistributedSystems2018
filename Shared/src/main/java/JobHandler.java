import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import akka.actor.Actor;


public class JobHandler<E> implements Serializable {

    public SerializableSupplier job;
    public E result;
    public Exception e;
    private String id;
    public String parentId;
    public boolean done = false;

    /**
     * Object storing all functions to use on the head node, MUST BE SERIALIZABLE!!!
     * @param job
     */
    public JobHandler(SerializableSupplier job) {
        this.job = job;
    }

    /**
     * Used to update the result, only to be called by the worker
     * @param result
     */
    public void setResult(E result) {
        this.result = result;
        this.done = true;
    }
    /**
    Function to be called by the scheduler
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Function to be called by the scheduler
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * If a job is executed multiple times, it should be cloned and it can be used to set the original job
     * @param id
     */
    public void setParentId(String id) {
        this.parentId = id;
    }

    /**
     * Gets the parent ID of the clone
     * @return
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * Used to get the response, can be an exception or a real result
     * @return
     * @throws Exception
     */
    public E getResult() throws Exception {
        if(this.e != null) {
            throw this.e;
        }
        return this.result;
    }

    /**
     * Used to set a possible exception, only to be called by the worker
     * @param e
     */
    public void setException(Exception e) {
        this.e = e;
        this.done = true;
    }

    @Override
    public JobHandler<E> clone() {
        JobHandler<E> result = new JobHandler<E>(this.job);
        result.setId(this.getId());
        result.e = this.e;
        result.result = this.result;
        result.setParentId(this.getParentId());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof JobHandler) {
            JobHandler jobHandler = (JobHandler)obj;
            boolean exceptionEqual = (this.e == null && jobHandler.e == null) || (this.e != null && jobHandler.e != null && this.e.getClass().equals(jobHandler.e.getClass()));
            boolean resultEqual = (this.result == null && jobHandler.result == null) || (this.result != null && jobHandler.result != null && this.result.equals(jobHandler.result));
            return exceptionEqual && resultEqual;

        } else {
            return super.equals(obj);
        }
    }
}
