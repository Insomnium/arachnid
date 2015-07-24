package net.ins.arachnid.engine.util;

import net.ins.arachnid.dao.impl.AudioDaoHibernateImpl;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by ins on 5/24/15.
 */
public class FSUtil {
    public static AudioDaoHibernateImpl readStorage(File fileDB) throws IOException, ClassNotFoundException {
        FileInputStream fin = FileUtils.openInputStream(fileDB);
        ObjectInputStream oos = new ObjectInputStream(fin);
        return (AudioDaoHibernateImpl) oos.readObject();
    }

    public static void saveStorage(AudioDaoHibernateImpl storage, File fileDB) throws IOException {
        FileOutputStream fos = FileUtils.openOutputStream(fileDB);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(storage);
    }
}
