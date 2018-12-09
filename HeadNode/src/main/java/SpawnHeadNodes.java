import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Address;

import java.util.Timer;

public class SpawnHeadNodes {

    /**
     * Used to spawn 3 head nodes
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    //TODO maybe all on separate system?
    public static void main(String[] args) throws IOException, InterruptedException{

        //create the Actor
        ActorSystem root = ActorSystem.create("root-node");
        List<Integer> headNodeIds = Utils.getListOfLength(Configuration.NUMBER_OF_HEADNODES);
        List<Integer> workerIds = Utils.getListOfLength(Configuration.NUMBER_OF_BYZANTIAN_ERRORS);

        List<ActorRef> headNodes = new ArrayList<ActorRef>();

        Address remoteAdress = root.provider().getDefaultAddress();
        try {
            // Create reference for top level actors (head nodes)
            for (int i = 0; i < headNodeIds.size(); i++) {
                ActorRef headRef = root.actorOf(HeadNode.props(headNodeIds.get(i)), "headNodeId-" + headNodeIds.get(i));
                headNodes.add(headRef);
            }
            System.out.println("COPY PASTE THE FOLLOWING 3 LINES INTO THE PROGRAM ARGUMENTS:");
            for(ActorRef node : headNodes) {
                node.tell(new HeadNode.PropagateHeadNodes(headNodes), ActorRef.noSender());
                System.out.println(node.path().toSerializationFormatWithAddress(remoteAdress));
            }
            //createInitalWorkers(root, workerIds, headNodes);
            //runScheduledTask(headNodes);
            //System.out.println("Press ENTER to exit the system");
            System.in.read();
        } finally {
            root.terminate();//also terminates all other nodes
        }

    }

    /*public static void createInitalWorkers(ActorSystem root, List<Integer> workerIds, List<ActorRef> headNodes) {
        //create inital pool of workers, other processes can create these as well
        for(int i = 0; i < workerIds.size(); i++) {
            ActorRef workerRef = root.actorOf(WorkerNode.props(workerIds.get(i), headNodes), "workerId-" + workerIds.get(i));
        }
    }*/

    public static void runScheduledTask(List<ActorRef> headNodes) throws InterruptedException{
        Timer time = new Timer(); //timer object
        TaskScheduler task = new TaskScheduler(headNodes); // taskScheduler object
        time.schedule(task, 0, 3000); // create task every 3 sec

        for (int i = 0; i <= 5; i++) {
            System.out.println("Execution in Main Thread...." + i);
            Thread.sleep(2000); //sleep for 2 sec
            if (i == 5) {
                System.out.println("Termination. Goodbye");
                System.exit(0);
            }
        }
    }

}