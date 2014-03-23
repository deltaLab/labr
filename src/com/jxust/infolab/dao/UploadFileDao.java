package com.jxust.infolab.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.jxust.infolab.entities.UploadedFile;
import com.jxust.infolab.utils.EntityManagerHelper;


public class UploadFileDao {
	private static Logger log = Logger.getLogger(UploadFileDao.class) ;
	public void saveUploadedFiles(List<UploadedFile> list){
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		try {
			em.getTransaction().begin();
			for(UploadedFile file:list){
				em.persist(file);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			log.info("本次成功上传文件失败"+e);
		}finally{
			em.close();
		}
		log.info("本次成功上传"+list.size()+"个文件");
	}
}
