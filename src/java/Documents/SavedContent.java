/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Documents;

import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author nelli
 */
public class SavedContent extends TagSupport{
    private String content;
    public SavedContent(String content){
        this.content=content;
        //pageContext.getOut().print("+content+");
    }
    
}
