import akka.actor.ActorRef;

import java.util.List;

public class Messages {

    public class RegisterWorkerToHead {
        public WorkerNode workerNode;

        public RegisterWorkerToHead(WorkerNode worker) {
            this.workerNode = worker;
        }
    }

    public class RemoveWorkerFromHead {
        public WorkerNode workerNode;

        public RemoveWorkerFromHead(WorkerNode worker) {
            this.workerNode = worker;
        }
    }

    public class GetJobFromClient {
        public JobHandler jobHandler;
        public ClientActor clientActor;

        public GetJobFromClient(JobHandler jobHandler, ClientActor clientActor) {
            this.jobHandler = jobHandler;
            this.clientActor = clientActor;
        }
    }

    public class GetJobFromWorker {
        public JobHandler jobHandler;
        public WorkerNode workerNode;

        public GetJobFromWorker(JobHandler worker, WorkerNode workerNode) {
            this.jobHandler = worker;
            this.workerNode = workerNode;
        }
    }

    public class CrashingHeadNode {
        public HeadNode headNode;
        public CrashingHeadNode(HeadNode headNode) {
            this.headNode = headNode;
        }
    }

    public class PropagateHeadNodes {
        public List<ActorRef> headNodes;
        public PropagateHeadNodes(List<ActorRef> headNodes) {
            this.headNodes = headNodes;
        }
    }

    public class SendJobToWorker {
        public JobHandler job;
        public SendJobToWorker(JobHandler job) {
            this.job = job;
        }
    }

    public class GetJobFromHead {
        public JobHandler job;
        GetJobFromHead(JobHandler job) {
            this.job = job;
        }
    }

    public RegisterWorkerToHead registerWorkerToHead(WorkerNode worker) {
        return new RegisterWorkerToHead(worker);
    }

    public RemoveWorkerFromHead removeWorkerToHead(WorkerNode worker) {
        return new RemoveWorkerFromHead(worker);
    }

    public GetJobFromClient getJobFromClient(JobHandler job, ClientActor clientActor) {
        return new GetJobFromClient(job, clientActor);
    }

    public GetJobFromWorker getJobFromWorker(JobHandler job, WorkerNode worker) {
        return new GetJobFromWorker(job, worker);
    }

    public CrashingHeadNode crashingHeadNode(HeadNode headNode) {
        return new CrashingHeadNode(headNode);
    }

    public PropagateHeadNodes propagateHeadNodes(List<ActorRef> headNodes) {
        return new PropagateHeadNodes(headNodes);
    }

    public SendJobToWorker sendJobToWorker(JobHandler job) {
        return new SendJobToWorker(job);
    }

    public GetJobFromHead getJobFromHead(JobHandler job) {
        return new GetJobFromHead(job);
    }

}
