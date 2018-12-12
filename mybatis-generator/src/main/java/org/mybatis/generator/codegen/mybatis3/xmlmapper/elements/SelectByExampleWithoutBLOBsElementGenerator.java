/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import com.mybatis.generator.constant.Constant;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectByExampleWithoutBLOBsElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectByExampleWithoutBLOBsElementGenerator() {
        super();
    }

    /**
     *   <select id="selectByExample" resultMap="BaseResultMap" parameterType="com.mybatis.generator.pojo.QueryExample" >
    		select
    		<if test="distinct" >
      			distinct
    		</if>
    		<include refid="Base_Column_List" />
    		from activity_bargain_good_logistics_info t
    		<if test="_parameter != null" >
      			<include refid="com.mybatis.generator.util.UtilMapper.Example_Where_Clause" />
    		</if>
    		<if test="orderByClause != null" >
      			order by ${orderByClause}
    		</if>
    		<if test="limit != null and limit > 0" >
      			limit #{offset},#{limit}
    		</if>
  		</select>
     */
    @Override
    public void addElements(XmlElement parentElement) {
    	//com.mybatis.generator.pojo.QueryExample 此处用公用的，所以改掉原有的com.mybatis.generator.pojo.ActivityBargainGoodLogisticsInfoExample类似的
        //String fqjt = introspectedTable.getExampleType();
    	String fqjt = Constant.QUERY_EXAMPLE;

        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                introspectedTable.getSelectByExampleStatementId()));
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select")); //$NON-NLS-1$
        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "distinct")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
        answer.addElement(ifElement);

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable
                .getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
            answer.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(getBaseColumnListElement());

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        //此处给表明取个别名 t
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime() +" t");
        answer.addElement((new TextElement(sb.toString())));
        //设置where语句
        answer.addElement(getExampleIncludeElement());
        
        //次数是排序
        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${orderByClause}")); //$NON-NLS-1$
        answer.addElement(ifElement);
        
        //此处新增一个limit分页
        ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "limit != null and limit > 0")); 
        ifElement.addElement(new TextElement("limit #{offset},#{limit}"));
        answer.addElement(ifElement);
        
        if (context.getPlugins()
                .sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
