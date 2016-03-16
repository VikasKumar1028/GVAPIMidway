package com.gv.midway.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.gv.midway.device.request.pojo.Cell;
import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.gv.midway.device.response.pojo.BatchInsertResponse;
import com.gv.midway.device.response.pojo.InsertCell;



public class BatchTask {

	private MongoTemplate mongoTemplate;
	
	private String action;
	
	private Devices devices;
	
	private int successCount=0;
	
	private int failCount=0;
	
    private List<InsertCell> insert_cells = new ArrayList<InsertCell>();
	
	private List<Cell> error_cells=new ArrayList<Cell>();
	
	public BatchTask(MongoTemplate mongoTemplate,String action,Devices devices){
		
		this.mongoTemplate=mongoTemplate;
		this.action=action;
		this.devices=devices;
	}
	
	public Object doBatchJob(){
		
		BatchExecutor batchExecutor=BatchExecutor.getBatchExecutor();
		ExecutorService executorService=batchExecutor.getExecutorService();
		
		Device[] deviceArr=devices.getDevices();
		CompletionService<Object> compService = new ExecutorCompletionService<Object>(executorService);
		for (final Device device : deviceArr) {
          
           compService.submit(new Callable<Object>() {
			
			public Object call() throws Exception {
				// TODO Auto-generated method stub

					try {
						device.setStatus("activate");
						// device.setIpAddress("127.0.0.1");
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ssZ");
						device.setLastUpdated(sdf.format(new Date()));

						mongoTemplate.insert(device);
					}

					catch (Exception e) {
						failCount=failCount+1;
						Cell cell=device.getCell();
                        error_cells.add(cell);
						return cell;
					}
                   
					successCount=successCount+1;
					InsertCell insertCell= new InsertCell();
					insertCell.setId(device.getId());
					insert_cells.add(insertCell);
				return insertCell;
			}
		});
          }
	
		
		for (final Device device : deviceArr) {
			
			try {
				 compService.take().get(); // find the first completed task
				
			} 
			catch (InterruptedException e) 
			{
			   
			} 
			
			catch (ExecutionException e) {
				
				
			}
		}
		
		BatchInsertResponse batchInsertResponse=new BatchInsertResponse();
		batchInsertResponse.setMessage("Success");
		batchInsertResponse.setInsert_cells(insert_cells);
		batchInsertResponse.setError_count(failCount);
		batchInsertResponse.setInsert_count(successCount);
		batchInsertResponse.setError_cells(error_cells);
		
		
	
		return batchInsertResponse;
	}
	
	
}
