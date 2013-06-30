package com.saternos.bookshop;
import com.saternos.bookshop.domain.Book;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;

@RooWebJson(jsonObject = Book.class)
@Controller
@RequestMapping("/books")
@RooWebScaffold(path = "books", formBackingObject = Book.class)
public class BookController {
}
