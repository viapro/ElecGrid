package com.viapro.elec.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viapro.elec.bean.ElecApplicationTemplate;
import com.viapro.elec.service.ElecApplicationTemplateService;
import com.viapro.elec.service.WorkflowBaseService;
import com.viapro.elec.util.FileUploadUtil;

@Service(ElecApplicationTemplateService.BEAN_NAME)
@Transactional(readOnly = true)
public class ElecApplicationTemplateServiceImpl extends WorkflowBaseService implements ElecApplicationTemplateService {

	@Override
	@Transactional(readOnly = false)
	public void save(ElecApplicationTemplate at) {
		String path = FileUploadUtil.saveFile(at.getUpload());
		at.setPath(path);
		atDao.save(at);
	}

	@Override
	public List<ElecApplicationTemplate> findAll() {
		return atDao.getAll();
	}

	@Override
	public ElecApplicationTemplate getApplicationTemplateById(Long id) {
		return atDao.getById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(ElecApplicationTemplate at) {
		if (at.getUpload() != null) {
			// 1. delete old archive
			new File(at.getPath()).delete();
			// 2. save new archive and update AT in DB
			at.setPath(FileUploadUtil.saveFile(at.getUpload()));
		}
		atDao.update(at);
	}

	@Override
	public InputStream getApplicationTemplateResource(ElecApplicationTemplate model) {
		try {
			ElecApplicationTemplate at = atDao.getById(model.getId());
			model.setName(at.getName());
			model.setInputStream(new FileInputStream(at.getPath()));
			// return new FileInputStream(at.getPath()) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteApplicationTemplate(ElecApplicationTemplate model) {
		ElecApplicationTemplate at = atDao.getById(model.getId());
		new File(at.getPath()).delete();
		atDao.delete(model.getId());
	}

}
