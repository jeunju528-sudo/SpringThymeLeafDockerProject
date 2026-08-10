package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.DataBoardMapper;
import com.sist.web.vo.DataBoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataBoardServiceImpl implements DataboardService{
	private final DataBoardMapper dataBoardMapper;

	@Override
	public List<DataBoardVO> databoardListData(int start) {
		return dataBoardMapper.databoardListData(start);
	}

	@Override
	public int databoardTotalPage() {
		return dataBoardMapper.databoardTotalPage();
	}

	@Override
	public void databoardInsert(DataBoardVO vo) {
		dataBoardMapper.databoardInsert(vo);
	}
}
