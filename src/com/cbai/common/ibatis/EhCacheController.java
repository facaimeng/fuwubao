package com.cbai.common.ibatis;

import java.net.URL;
import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.ibatis.sqlmap.engine.cache.CacheController;
import com.ibatis.sqlmap.engine.cache.CacheModel;

/**
 * EhCache Implementation of the
 * {@link com.ibatis.sqlmap.engine.cache.CacheController} interface to be able
 * to use EhCache as a cache implementation in iBatis. You can configure your
 * cache model as follows, by example, in your sqlMapping files:
 * 
 * <pre>
 * <code>
 * <cacheModel id="myCache" readOnly="true" serialize="false"
 * 	type="com.ibatis.sqlmap.engine.cache.EhCacheController" > 
 * 	<property name="configLocation"
 * 		value="/path-to-ehcache.xml"/> 
 * </cacheModel> </code>
 * </pre>
 * 
 * Alternatively, you can use a type alias in your type attribute and defining
 * the class with a <code><typeAlias></code> declaration:
 * 
 * <pre>
 * <code>
 * <sqlMapConfig>
 * 	<typeAlias alias="EHCACHE" 
 * 		type="com.ibatis.sqlmap.engine.cache.ehcache.EhCacheController" />
 * </sqlMapConfig>
 * </code>
 * </pre>
 * 
 */
public class EhCacheController implements CacheController {

	/**
	 * The EhCache CacheManager.
	 */
	private CacheManager cacheManager;

	public static final String CONFIG_LOCATION = "configLocation";

	/**
	 * Default Configure Location
	 */
	public static final String DEFAULT_CONFIG_LOCATION = "/ehcache.xml";

	/**
	 * Flush a cache model.
	 * 
	 * @param cacheModel
	 *            - the model to flush.
	 */
	public void flush(CacheModel cacheModel) {
		getCache(cacheModel).removeAll();
	}

	/**
	 * Get an object from a cache model.
	 * 
	 * @param cacheModel
	 *            - the model.
	 * @param key
	 *            - the key to the object.
	 * @return the object if in the cache, or null(?).
	 */
	public Object getObject(CacheModel cacheModel, Object key) {
		Object result = null;
		Element element = getCache(cacheModel).get(key);
		if (element != null) {
			result = element.getObjectValue();
		}
		return result;

	}

	/**
	 * Put an object into a cache model.
	 * 
	 * @param cacheModel
	 *            - the model to add the object to.
	 * @param key
	 *            - the key to the object.
	 * @param object
	 *            - the object to add.
	 */
	public void putObject(CacheModel cacheModel, Object key, Object object) {
		getCache(cacheModel).put(new Element(key, object));
	}

	/**
	 * Remove an object from a cache model.
	 * 
	 * @param cacheModel
	 *            - the model to remove the object from.
	 * @param key
	 *            - the key to the object.
	 * @return the removed object(?).
	 */
	public Object removeObject(CacheModel cacheModel, Object key) {
		Object result = this.getObject(cacheModel, key);
		getCache(cacheModel).remove(key);
		return result;
	}

	/**
	 * Gets an EH Cache based on an iBatis cache Model.
	 * 
	 * @param cacheModel
	 *            - the cache model.
	 * @return the EH Cache.
	 */
	private Cache getCache(CacheModel cacheModel) {
		String cacheName = cacheModel.getId();
		Cache cache = cacheManager.getCache(cacheName);
		return cache;
	}

	/**
	 * Shut down the EH Cache CacheManager.
	 */
	public void finalize() {
		if (cacheManager != null) {
			cacheManager.shutdown();
		}
	}

	/**
	 * Configure a cache controller. Initialize the EH Cache Manager as a
	 * singleton.
	 * 
	 * @param props
	 *     - the properties object continaing configuration information.
	 */
	@Override
	public void setProperties(Properties props) {
		
		String configLocation = props.getProperty(CONFIG_LOCATION);
		// if can not found ehcache.xml from configLocaion,
		// use default configure file.
		if (configLocation == null) {
			configLocation = DEFAULT_CONFIG_LOCATION;
		}
		URL url = getClass().getResource(configLocation);
		cacheManager = CacheManager.create(url);
		
	}
}
