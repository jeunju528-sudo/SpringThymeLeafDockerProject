package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.DataBoardVO;

public interface DataboardService {
	public List<DataBoardVO> databoardListData(int start);

	public int databoardTotalPage();

	public void databoardInsert(DataBoardVO vo);
}
