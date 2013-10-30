package com.viapro.elec.service;

import java.io.InputStream;
import java.util.List;

import com.viapro.elec.bean.ElecApplicationTemplate;

public interface ElecApplicationTemplateService {
	public static final String BEAN_NAME = "com.viapro.elec.service.impl.ElecApplicationTemplateServiceImpl";

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午10:19:41
	 * @Parameters:
	 * @Return:void
	 */
	void save(ElecApplicationTemplate at);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 上午11:09:19
	 * @Parameters:
	 * @Return:List<ElecApplicationTemplate>
	 */
	List<ElecApplicationTemplate> findAll();

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 下午12:22:27
	 * @Parameters:
	 * @Return:Object
	 */
	ElecApplicationTemplate getApplicationTemplateById(Long id);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 下午12:42:11
	 * @Parameters:
	 * @Return:void
	 */
	void update(ElecApplicationTemplate at);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 下午1:12:00
	 * @Parameters:
	 * @Return:InputStream
	 */
	InputStream getApplicationTemplateResource(ElecApplicationTemplate model);

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-7 下午1:32:05
	 * @Parameters:
	 * @Return:void
	 */
	void deleteApplicationTemplate(ElecApplicationTemplate model);
}
