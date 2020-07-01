package ch.so.agi;

import java.util.ArrayList;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import ch.interlis.ili2c.Ili2c;
import ch.interlis.ili2c.Ili2cException;
import ch.interlis.ili2c.config.Configuration;
import ch.interlis.ili2c.metamodel.TransferDescription;
import ch.interlis.ilirepository.IliManager;

public class Ili2cApi {
    
    @CEntryPoint(name = "getLastModelName")
    public static CCharPointer getLastModelName(IsolateThread thread, CCharPointer fileName) {
        IliManager manager = new IliManager();
        String repositories[] = new String[] {
                "http://models.interlis.ch/", 
                "http://models.kkgeo.ch/", 
                "http://models.geo.admin.ch/" 
        };
        
        manager.setRepositories(repositories);
        
        ArrayList<String> ilifiles = new ArrayList<String>();
        ilifiles.add(CTypeConversion.toJavaString(fileName));
        
        Configuration config;
        TransferDescription td = null;
        try {
            config = manager.getConfigWithFiles(ilifiles);
            td = Ili2c.runCompiler(config);
        } catch (Ili2cException e) {
            e.printStackTrace();
        }
                
        if (td == null) {
            // Bricht ganzes C-Programm ab. Muss anders gelöst werden.
            throw new IllegalArgumentException("INTERLIS compiler failed");
        }
                
        return CTypeConversion.toCString(td.getLastModel().getName()).get();
    }
}
