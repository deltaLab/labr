package com.jxust.infolab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.jxust.infolab.beans.StudentShowBean;
import com.jxust.infolab.utils.EntityManagerHelper;

public class StudentsShowDao {
	private static Logger log = Logger.getLogger(StudentsShowDao.class);

	public EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	public List<StudentShowBean> lodPic(int offset, int pageSize) {
		List<StudentShowBean> studentShowList = new ArrayList<StudentShowBean>();
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		String jpql = "select uf.id,uf.saveName,uf.userName ,ss.year,ss.gender,ss.subject, ss.classNo from UploadedFile uf ,StudentShow ss where uf.userName=ss.name";
		try {
			Query query = getEntityManager().createQuery(jpql);
			query.setFirstResult(offset);
			query.setMaxResults(pageSize);
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();

			if (results != null && results.size() > 0) {
				for (Object[] cols : results) {
					StudentShowBean ssBean = new StudentShowBean();
					bean: for (int i = 0; i < cols.length; i++) {
						switch (i) {
						case 0:
							if (cols[i] != null) {
								ssBean.setPicId((Integer) cols[i]);
							} else {
								break bean;
							}
							break;
						case 1:
							if (cols[i] != null) {
								ssBean.setPicPath((String) cols[i]);
							} else {
								break bean;
							}
							break;
						case 2:
							if (cols[i] != null) {
								ssBean.setName((String) cols[i]);
							}
							break;
						case 3:
							if (cols[i] != null) {
								ssBean.setYear((Integer) cols[i]);
							}
							break;
						case 4:
							if (cols[i] != null) {
								ssBean.setGender((String) cols[i]);
							}
							break;
						case 5:
							if (cols[i] != null) {
								ssBean.setSubject((String) cols[i]);
							}
							break;
						default:
							if (cols[i] != null) {
								ssBean.setClassNo((String) cols[i]);
							}
						}
					}
					if (ssBean.getPicId() != 0 && ssBean.getPicPath() != null) {
						studentShowList.add(ssBean);
					}
				}
			}
		} catch (Exception e) {
			log.info("本次查询图片失败" + e);
		} finally {
			em.close();
		}
		log.info("查询图片成功,共"+studentShowList.size()+"个对象");
		return studentShowList;
	}
}
