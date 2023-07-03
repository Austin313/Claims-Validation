package com.aa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.aa.POJOS.CLAIM;
import com.aa.POJOS.RESDIT;
import com.opencsv.CSVWriter;

public class Main {
	
	
	/*
	 * Steps:
	 * 1.Retrieve Excel File with all data
	 * 2.For each record create a pojo that contains that information
	 * 3. Check if the next segment is the same, if so update with which ever has the latest data
	 * 4.Move to the next resdit line, again check the same if the file has the same records
	 * 5.When we reachthe next mailtag, grab the claims file, and update information with pulled data.
	 * 6. Repeat with the rest of the data 
	 * 7. Send updated sheet in email to Jammi.
	 */
	@SuppressWarnings("null")
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the path of Exported file ..");
		String fileName1 = sc.nextLine();
		
		System.out.println("Enter the path of iCargo Supplied File ..");
		String fileName2 = sc.nextLine();
		
		File fileWithInformation = new File(fileName1);
		File claimsFile = new File(fileName2);
		sc.close();
		BufferedReader reader = null;
		String line = "";
		List<RESDIT> resdits = new ArrayList<RESDIT>();
		try {
			reader = new BufferedReader(new FileReader(fileWithInformation));
			String[] row = null;
			String trash = "";
			trash = reader.readLine();
			String mailtag = "";
			String event = "";
			String loc = "";
			String dtm = "";
			String rsd = "";
			String seqNo = "";
			while((line = reader.readLine())!= null) {
				row = line.split(",", -1);
				System.out.println(line);
				mailtag = row[0];
				event = row[1];
				loc = row[2];
				dtm = row[3];
				rsd = row[4];
				seqNo = row[5];
				RESDIT currentResdit = null;
				for(RESDIT r: resdits) {
					//System.out.println(r.getMailtag()+" -- "+mailtag);
					if(r.getMailtag().equals(mailtag)) {
						currentResdit = r;
						break;
					}
				}
				if(currentResdit == null) {
					RESDIT resdit = new RESDIT(mailtag);
					if(event.equals("21")) {
						resdit.setDtm21(dtm);
						resdit.setLoc21(loc);
						resdit.setRcvsnddat21(rsd);
						resdit.setSeqNo21(seqNo);
					}
					if(event.equals("24")) {
						resdit.setDtm24(dtm);
						resdit.setLoc24(loc);
						resdit.setRcvsnddat24(rsd);
						resdit.setSeqNo24(seqNo);
					}
					if(event.equals("74")) {
						resdit.setDtm74(dtm);
						resdit.setLoc74(loc);
						resdit.setRcvsnddat74(rsd);
						resdit.setSeqNo74(seqNo);
					}
					System.out.println("creating resdit object");
					resdits.add(resdit);
					continue;
				}
				if(event.equals("21") && !currentResdit.getLoc21().isBlank()||event.equals("24") &&  !currentResdit.getLoc24().isBlank() || event.equals("74") && !currentResdit.getLoc74().isBlank()) {
					//checks if mailtag and event is the same
					//changes the event time -- needs to check if the time is later than the original then set if it is.
					System.out.println("date is set.. comparing");
					if(event.equals("21")) {
						if(compareDates(currentResdit.getRcvsnddat21(),rsd,"rsd").equals(rsd)) {
							System.out.println("date is later, updating..");
							currentResdit.setDtm21(dtm);
							currentResdit.setLoc21(loc);
							currentResdit.setRcvsnddat21(rsd);
							currentResdit.setSeqNo21(seqNo);
						}
						
					}
					if(event.equals("24")) {
						if(compareDates(currentResdit.getRcvsnddat24(),rsd,"rsd").equals(rsd)) {
							System.out.println("date is later, updating..");
							currentResdit.setDtm24(dtm);
							currentResdit.setLoc24(loc);
							currentResdit.setRcvsnddat24(rsd);
							currentResdit.setSeqNo24(seqNo);
						}
					}
					if(event.equals("74")) {
						if(compareDates(currentResdit.getRcvsnddat74(),rsd,"rsd").equals(rsd)) {
							System.out.println("date is later, updating..");
							currentResdit.setDtm74(dtm);
							currentResdit.setLoc74(loc);
							currentResdit.setRcvsnddat74(rsd);
							currentResdit.setSeqNo74(seqNo);
						}
					}
					
				} else if(currentResdit.getMailtag().equals(mailtag) && currentResdit.getLoc21().isEmpty() || currentResdit.getMailtag().equals(mailtag) && currentResdit.getLoc24().isEmpty()||currentResdit.getMailtag().equals(mailtag) && currentResdit.getLoc74().isEmpty()) {
					//if not checks just he mailtag
					//sets event and event time
					System.out.println(currentResdit.getLoc21());
					if(event.equals("21")) {
						currentResdit.setDtm21(dtm);
						currentResdit.setLoc21(loc);
						currentResdit.setRcvsnddat21(rsd);
						currentResdit.setSeqNo21(seqNo);
					}
					if(event.equals("24")) {
						currentResdit.setDtm24(dtm);
						currentResdit.setLoc24(loc);
						currentResdit.setRcvsnddat24(rsd);
						currentResdit.setSeqNo24(seqNo);
					}
					if(event.equals("74")) {
						currentResdit.setDtm74(dtm);
						currentResdit.setLoc74(loc);
						currentResdit.setRcvsnddat74(rsd);
						currentResdit.setSeqNo74(seqNo);
					}
					System.out.println("Adding values");
				}
			}
			reader.close();
			System.out.println(resdits.size());
			//For the second file we will need to Map these to correction objects themselves
			//We will compare times from associated times and update based on later dates
			//Once all objects have been iterated over write to CSV.
			reader = new BufferedReader(new FileReader(claimsFile));
			String header = "";
			header = reader.readLine();
			line = "";
			row = null;
			List<CLAIM> claims = new ArrayList<CLAIM>();
			while((line = reader.readLine())!= null) {
				row = line.split(",",-1);
				CLAIM claim = new CLAIM(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],row[19],row[20],row[21]);
				claims.add(claim);				
			}
			reader.close();
			claims.forEach(x -> {
				String currentMailtag = x.getMALIDR();
				if(currentMailtag.charAt(0) == '\"') {
					currentMailtag = currentMailtag.substring(1, currentMailtag.length() -1);
				}
				RESDIT resdit = null;
				for(RESDIT r: resdits) {
					if(currentMailtag.equals(r.getMailtag())) {
						resdit = r;
						break;
					}
				}
				if(resdit == null) {
					System.out.println("Mailtag not found going next.." + currentMailtag);
					claims.remove(x);
					return;
				}
				x.setPOSSCNAPCOD(resdit.getLoc74());
				try {
					x.setACPDAT(resdit.getDtm74());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				x.setLODSCNARPCOD(resdit.getLoc24());
				try {
					x.setLODSCNDAT(resdit.getDtm24());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				x.setDLVARPCOD(resdit.getLoc21());
				try {
					x.setACTDLVDAT(resdit.getDtm21());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				x.setACTDSTCOD(resdit.getLoc21());
				x.setACTORGCOD(resdit.getLoc74());
			});
			
			System.out.println("done, printing to CSV ...");
			File newCSV = new File("resultCSV.csv");
			FileWriter outputfile = new FileWriter(newCSV);
			CSVWriter writer = new CSVWriter(outputfile);
			String[] headerLine = header.split(",");
			List<String[]> dataToWrite = new ArrayList<>();
			dataToWrite.add(headerLine);
			for(CLAIM c: claims) {
				dataToWrite.add(c.toArray());
			}
			writer.writeAll(dataToWrite);
			
			writer.close();
			System.out.println("File written..");
			System.out.print(newCSV.getAbsolutePath());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String compareDates(String x, String y, String mode){
		try {
			SimpleDateFormat sdformat = null;
			if(mode == "dtm") {
				sdformat = new SimpleDateFormat("yyMMddHHmm");
			}
			if(mode == "rsd") {
				sdformat = new SimpleDateFormat("d-MMM-y H:m");
			}
			Date d1 = sdformat.parse(x);
			Date d2 = sdformat.parse(y);
			if(d1.compareTo(d2) > 0) {
				return x;
			} else if(d1.compareTo(d2) < 0) {
				return y;
			} else {
				return y;
			}
		}catch(ParseException e) {
			try {
				SimpleDateFormat sdformat = null;
				if(mode == "dtm") {
					sdformat = new SimpleDateFormat("yyMMddHHmm");
				}
				if(mode == "rsd") {
					sdformat = new SimpleDateFormat("M/d/y H:m");
				}
				Date d1 = sdformat.parse(x);
				Date d2 = sdformat.parse(y);
				if(d1.compareTo(d2) > 0) {
					return x;
				} else if(d1.compareTo(d2) < 0) {
					return y;
				} else {
					return y;
				}
			}catch(ParseException m) {
				System.out.println("===== Date time format needs to be added for below");
				m.getMessage();
				return x;
			}
		}
		
		
	}

}
