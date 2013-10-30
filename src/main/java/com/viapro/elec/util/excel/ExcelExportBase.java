package com.viapro.elec.util.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface ExcelExportBase<T extends Serializable> {

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-10 下午10:41:35
	 * @Parameters:
	 * @Return:ArrayList<String>
	 */
	ArrayList<String> getFieldName();

	/**
	 * @Name:
	 * @Description:
	 * @Author:ViaPro
	 * @Version:V1.00
	 * @Create Date:2013-10-10 下午10:41:52
	 * @Parameters:
	 * @Return:ArrayList<ArrayList<String>>
	 */
	ArrayList<ArrayList<String>> getFieldData(List<String> whereConditions, List<String> params);

}
