package lv.challenge.servlets.mvc.controllers;

import lv.challenge.services.common.HTMLStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/HTML_editor")
public class HTMLEditorController {
    @Autowired
    HTMLStoreService htmlStoreService;

    @PostMapping("/footer")
    @ResponseBody
    protected void editFooter(@RequestParam String text) {
        htmlStoreService.writeToHTMLFileStore(text, "footer", LocaleContextHolder.getLocale());
    }
}
