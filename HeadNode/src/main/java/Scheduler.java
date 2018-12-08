import akka.actor.ActorRef;

public class Scheduler implements PolicyInterface {

    public PolicyInterface policy;
    /**
     * Wrapper class for the policy, choise of policy should be made here.
     */
    public Scheduler(HeadNodeState state, ActorRef headNode) {
        //Choose which policy to used based on configuration
        if(Configuration.policy == Configuration.Policies.LOCK_STEP) {
            this.policy = new LockStepPolicy(state, headNode);
        }
        else if(Configuration.policy == Configuration.Policies.MAXIMIZE) {
            //TODO make maximize policy
            //this.policy = new LockStepPolicy(state, headNode);
        } else {
            throw new Error("Unknown policy in configuration file");
        }
    }

    @Override
    public void update(JobHandler jobHandler, JobActor jobActor) {
        this.policy.update(jobHandler, jobActor);
    }

    @Override
    public boolean update(JobHandler jobHandler, WorkerData worker) {
        return this.policy.update(jobHandler, worker);
    }
}
