package com.saternos.bookshop;
import com.saternos.bookshop.domain.Author;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Author.class)
@Controller
@RequestMapping("/authors")
@RooWebScaffold(path = "authors", formBackingObject = Author.class)
public class AuthorController {
}
