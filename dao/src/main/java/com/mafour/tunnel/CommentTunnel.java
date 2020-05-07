package com.mafour.tunnel;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mafour.dao.CommentDO;
import com.mafour.mapper.CommentMapper;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentTunnel extends ServiceImpl<CommentMapper, CommentDO> {

  public List<CommentDO> findList(String bookName, String slug) {
    Wrapper<CommentDO> wrapper =
        new LambdaQueryWrapper<CommentDO>()
            .eq(CommentDO::getBookName, bookName)
            .eq(CommentDO::getSlug, slug)
            .orderByDesc(CommentDO::getCreateTime);
    return list(wrapper);
  }
}
