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

import java.util.Iterator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class BaseColumnListElementGenerator extends AbstractXmlElementGenerator {

    public BaseColumnListElementGenerator() {
        super();
    }
    /**
     * xml生成
     * <sql id="Base_Column_List" >
     *		id, activity_id, task_id, user_task_id, address_id, address, consigner, 
     *		mobile, express_no, express, express_coding, sending_type, delivery_status, 
     *		info, update_time, is_subscribe
  	 *	</sql>
  	 *类似这种的，此处，我修改成这种的
  	 *     t.id,t.activity_id......
     */
    @Override
    public void addElements(XmlElement parentElement) {
    	//此处相当于做这个配置 <sql id="Base_Column_List" >
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                introspectedTable.getBaseColumnListId()));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> iter = introspectedTable
                .getNonBLOBColumns().iterator();
        //id, activity_id, task_id, user_task_id, address_id, address, consigner, 
        //		mobile, express_no, express, express_coding, sending_type, delivery_status, 
        //		info, update_time, is_subscribe
        //此处在除id的其他列前，我都添加一个t.
        while (iter.hasNext()) {
            sb.append("t."+MyBatis3FormattingUtilities.getSelectListPhrase(iter
                    .next()));

            if (iter.hasNext()) {
                sb.append(", "); //$NON-NLS-1$
            }

            if (sb.length() > 80) {
                answer.addElement(new TextElement(sb.toString()));
                sb.setLength(0);
            }
        }

        if (sb.length() > 0) {
            answer.addElement((new TextElement(sb.toString())));
        }

        if (context.getPlugins().sqlMapBaseColumnListElementGenerated(
                answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
