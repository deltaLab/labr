package com.jxust.infolab.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.jxust.infolab.entities.UserInfo;
import com.jxust.infolab.utils.EntityManagerHelper;

public class UserInfoDao {
	private static Logger log = Logger.getLogger(UserInfoDao.class) ;
	public EntityManager getEntityManager()
	{
		return EntityManagerHelper.getEntityManager();
	}

	public UserInfo login(String userName,String password){
		UserInfo user = null;
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.clear();
		String jpql ="select UserInfo where userName=:userName and password=:password";
		try {
			Query query = getEntityManager().createQuery(jpql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			@SuppressWarnings("unchecked")
			List<UserInfo> results = query.getResultList();
			if(results!=null&&results.size()>0){
				user = results.get(0);
			}
		} catch (Exception e) {
			log.info("登录失败"+e);
		}finally{
			em.close();
		}
		log.info(user.getUserName()+"|"+user.getUserNick()+"登录成功");
		return user;
	}
}
