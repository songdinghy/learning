//package com.sd.learning;
//
//import javax.management.Query;
//import java.io.Serializable;
//import java.util.List;
//
//public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
//
//    Class<T> clazz;
//
//    public BaseDaoImpl() {
//        //getClass().getGenericSuperclass()返回表示此 Class
//        //所表示的实体（类、接口、基本类型或 void）的直接超类的 Type
//        //然后将其转换ParameterizedType
//        //getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组
//        //[0]就是这个数组中第一个了。
//
//        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
//        clazz = (Class<T>) pt.getActualTypeArguments()[0];
//    }
//
//    @Override
//    public void save(T entity) {
//        getHibernateTemplate().save(entity);
//    }
//
//    @Override
//    public void delete(Serializable id) {
//        getHibernateTemplate().delete(findObjectById(id));
//    }
//
//    @Override
//    public void update(T entity) {
//        getHibernateTemplate().update(entity);
//    }
//
//    @Override
//    public T findObjectById(Serializable id) {
//        return getHibernateTemplate().get(clazz, id);
//    }
//
//    @Override
//    public List<T> findObjects() {
//        Query query = (Query) getSession().createQuery("from " + clazz.getSimpleName());
//        return query.list();
//    }
//
//    @Override
//    public List<T> findObjects(QueryHelper queryHelper) {
//        Query listQuery = getSession().createQuery(queryHelper.getHql());
//        List<Object> parameters = queryHelper.getParameters();
//
//        if (parameters != null) {
//            for (int i = 0; i < parameters.size(); i++) {
//                listQuery.setParameter(i, parameters.get(i));
//            }
//        }
//        return listQuery.list();
//    }
//}