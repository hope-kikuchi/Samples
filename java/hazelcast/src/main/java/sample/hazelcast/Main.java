package sample.hazelcast;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Main {
    
    public static void main(String[] args) {
        ClasspathXmlConfig config = new ClasspathXmlConfig("my-hazelcast.xml");
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        
        int size = instance.getPartitionService().getPartitions().size();
        System.out.println("partition.size=" + size);
    }
}
