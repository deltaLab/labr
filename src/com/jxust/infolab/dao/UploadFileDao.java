package com.jxust.infolab.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<UploadedFile> getFiles(Map<String,String> params){
		List<UploadedFile> results = null;
		String fileType=params.get("fileType");
		String sOffset = params.get("offset");
		String sPagesize = params.get("pagesize");
		int offset =0;
		int pagesize =20;
		if(sOffset!=null){
			offset = Integer.parseInt(sOffset);
		}
		if(sPagesize!=null){
			pagesize = Integer.parseInt(sPagesize);
		}
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		StringBuffer jpql = new StringBuffer("select uf from UploadedFile uf");
		if(fileType!=null){
			jpql.append(" and fileType=:fileType ");
		}
		try {
			Query query = em.createQuery(jpql.toString());
			if(fileType!=null){
				query.setParameter("fileType", fileType);
			}
			query.setFirstResult(offset);
			query.setMaxResults(pagesize);
			results = query.getResultList();
		} catch (Exception e) {
			log.info("获取上传文件列表时失败"+e);
		}finally{
			if(results==null){
				results = new ArrayList<UploadedFile>();
			}
			em.close();
		}
		return results;
	}
}
