package com.gv.midway.dao.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;
import com.mongodb.WriteResult;

@Service
public class DeviceDaoImpl implements IDeviceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(DeviceDaoImpl.class); // Initializing

	/*
	 * @Autowired MongoDb grandVictorDB;
	 */
	@Autowired
	MongoTemplate mongoTemplate;

	public String insertDeviceDetails(Device device) {

		logger.info("saving in database");
		mongoTemplate.save(device);
		return device.getId();

	}

	public String insertDevicesDetailsInBatch(Devices devices) {

		 mongoTemplate.insertAll(Arrays.asList(devices.getDevices()));
		return "SUCCESS";
	}

	public String updateDevicesDetailsInBatch(Devices devices) {

		for (Device device : devices.getDevices()) {
			Query searchUserQuery = new Query(Criteria
					.where("cell.esn")
					.is(device.getCell().getEsn())
					.andOperator(
							Criteria.where("cell.sim").is(
									device.getCell().getSim())));

			Update update = new Update();
			update.set("bs_id", device.getBs_id());
			mongoTemplate.updateFirst(searchUserQuery, update, Device.class);

		}

		return null;
	}

	public String updateDeviceDetails(Device device) {

		Query searchUserQuery = new Query(Criteria
				.where("cell.esn")
				.is(device.getCell().getEsn())
				.andOperator(
						Criteria.where("cell.sim")
								.is(device.getCell().getSim())));

		WriteResult wr = mongoTemplate.updateFirst(searchUserQuery,
				Update.update("bs_id", device.getBs_id()), Device.class);

		return device.getId();

	}

}
