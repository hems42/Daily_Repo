package BackUp;

import java.io.*;
import java.nio.ByteBuffer;

public class BackUpManager {

    public static String DATABASE_MAINSOURCE_ABSOLUTE_PATH="/data/data/com.example.esh_ajanda/databases/DBPatients.db";
    public static String DATABASE_BACKUPSOURCE_ABSOLUTE_PATH="/sdcard/DBBackUp/DBPatients.db";


    public boolean getBackUpDataBase(String fileDB_PathIn, String fileDB_PathOut) {

        FileInputStream fis = null;

        File fileIn = new File(fileDB_PathIn);

        try {
            fis = new FileInputStream(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream fos = null;

        File fileOut = new File(fileDB_PathOut);

        try {
            try {
                fos = new FileOutputStream(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


            while (fis.read() > 0) {
                fis.read(byteBuffer.array());
                fos.write(byteBuffer.array());
            }

            fos.flush();

            fos.close();
            fis.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileOut.exists()&&fileOut.canRead())
        {
            return  true;
        }
        else
        {
            return false;
        }
    }

    public boolean LoadBackUpDataBase(String fileDB_PathIn, String fileDB_PathOut) {

        FileInputStream fis = null;

        File fileIn = new File(fileDB_PathIn);

        try {
            fis = new FileInputStream(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream fos = null;

        File fileOut = new File(fileDB_PathOut);

        try {
            try {
                fos = new FileOutputStream(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


            while (fis.read() > 0) {
                fis.read(byteBuffer.array());
                fos.write(byteBuffer.array());
            }

            fos.flush();

            fos.close();
            fis.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileOut.exists()&&fileOut.canRead())
        {
            return  true;
        }
        else
        {
            return false;
        }
    }


    public boolean getBackUpDataBase() {

        String fileDB_PathIn=DATABASE_MAINSOURCE_ABSOLUTE_PATH;
        String fileDB_PathOut=DATABASE_BACKUPSOURCE_ABSOLUTE_PATH;
        FileInputStream fis = null;

        return this.createFileFromPathSource(fileDB_PathIn,fileDB_PathOut);

    }

    public boolean LoadBackUpDataBase() {

        String fileDB_PathOut=DATABASE_MAINSOURCE_ABSOLUTE_PATH;
        String fileDB_PathIn=DATABASE_BACKUPSOURCE_ABSOLUTE_PATH;

        return this.createFileFromPathSource(fileDB_PathIn,fileDB_PathOut);
    }


    public boolean createFileFromPathSource(String pathInFile, String pathOutFile)
    {
        FileInputStream fis = null;

        File fileIn = new File(pathInFile);

        try {
            fis = new FileInputStream(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream fos = null;

        File fileOut = new File(pathOutFile);

        try {
            try {
                fos = new FileOutputStream(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            byte [] buffer= new byte[1024];
            int length;

            while ((length=fis.read(buffer)) > 0) {

                fos.write(buffer,0,length);
            }

            fos.flush();

            fos.close();
            fis.close();




        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileOut.exists()&&fileOut.canRead())
        {
            return  true;
        }
        else
        {
            return false;
        }

    }


}