package comworkmanager.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class DestroyListener {
	
	 static Logger logger = LoggerFactory.getLogger(DestroyListener.class);
	
    @PreDestroy
    public void destroy() {
    	logger.info(
          "Callback triggered - @PreDestroy. BackUp database in corso..");
        backupPGSQL();
        logger.info(
                "BackUp database finito..");
    }
    
    private void backupPGSQL(){
		  try{
		    Runtime r =Runtime.getRuntime();
		    //Path to the place we store our backup
		    String rutaCT = "backup_database\\bin\\";
		    String outputFolderName = "backup_database\\";
		    //PostgreSQL variables
		    String IP = "127.0.0.1";
		    String user = "postgres";
		    String dbase = "workmanager";
		    String password = "postgres";
		    Process p;
		    ProcessBuilder pb;
		    InputStreamReader reader;
		    BufferedReader buf_reader;
		    String line;
		    //We build a string with today's date (This will be the backup's filename)
		    java.util.TimeZone zonah = java.util.TimeZone.getTimeZone("GMT+1");
		    java.util.Calendar Calendario = java.util.GregorianCalendar.getInstance( zonah, new java.util.Locale("es"));
		    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd_HHmm");
		    StringBuffer date = new StringBuffer();
		    date.append(df.format(Calendario.getTime()));
		    java.io.File file = new java.io.File(rutaCT);
		    // We test if the path to our programs exists
		    if (file.exists()) {
		      // We then test if the file we're going to generate exist too. If so we will delete it
		      StringBuffer fechafile = new StringBuffer();
		      fechafile.append(outputFolderName);
		      fechafile.append(date.toString());
		      fechafile.append(".txt");
		      java.io.File ficherofile = new java.io.File(fechafile.toString());
		      if (ficherofile.exists()) {
		        ficherofile.delete();
		      }
		      r = Runtime.getRuntime();
		      pb = new ProcessBuilder(rutaCT + "pg_dump.exe", "-f", fechafile.toString(),
		          "-F","p","--inserts", "-v", "-h",IP,"-p","5433", "-U", user, dbase);
		      pb.environment().put("PGPASSWORD", password);
		      pb.redirectErrorStream(true);
		      p = pb.start();
		      try {
		        InputStream is = p.getInputStream();
		        InputStreamReader isr = new InputStreamReader(is);
		        BufferedReader br = new BufferedReader(isr);
		        String ll;
		        while ((ll = br.readLine()) != null) {
		        	logger.info(ll);
		        }
		      } catch (IOException e) {
		    	  logger.error("ERROR "+e.getMessage());
		      }
		    }
		  } catch(IOException x) {
			  logger.error("Could not invoke browser, command=");
			  logger.error("Caught: " + x.getMessage());
		  }
		}
}
