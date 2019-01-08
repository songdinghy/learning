package com.smart.elasticsearch.blog.domain;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.searchbox.annotations.JestId;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gaowenming
 * @create 2018-01-31 15:21
 * @desc
 **/
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = -929428991889962963L;
    @JestId
    private long id;
    private String title;
    private long createTime;
    private String content;
    private List<String> tags;
    private String categroy;
    private String author;
    private String lastUpdateTime;

    public static void main(String[] args) {
        int i = 0;
        Blog blog = new Blog();
        blog.setAuthor("Martin.S" + i);
        blog.setCategroy("类目" + i);
        blog.setContent("Strom的Topology包含Spout和Bolt两种节点类型，在这个案例中，可以使用Spout来对数据源进行处理（模拟产生数据），\n" +
                "然后将其发送到计算和的Bolt中，所以实际上这里只需要使用一个Spout节点和一个Bolt节点就可以了。");
        blog.setCreateTime(System.currentTimeMillis());
        blog.setId(i + 1);
        blog.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        blog.setTags(Lists.newArrayList("tag1", "tag2"));
        blog.setTitle("标题" + i);
        System.out.println("blog = [" + JSON.toJSONString(blog,true) + "]");

    }
}
