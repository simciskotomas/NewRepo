package cz.sde.DatabaseControl;


import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;


public class UUID {

    public static String generateUUID() {
        String uuid = null;
        EthernetAddress nic = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(nic);
        uuid = uuidGenerator.generate().toString();

//        System.out.print(uuid);

        return uuid;
    }

}
