package by.gordievich.task;

import by.gordievich.task.web.Controller;
import by.gordievich.task.web.Handler;
import by.gordievich.task.web.Server;
import by.gordievich.task.service.StoreFactory;
import by.gordievich.task.shop.Store;

import java.util.HashMap;
import java.util.Map;

import static by.gordievich.task.web.ConstantHttp.UrlPath.CHECK_PATH;

public class WebRunner {

    public static void main(String[] args) {

        Store store = StoreFactory.defaultStore();
        Map<String, Handler> handlers = new HashMap<>();
        handlers.put(CHECK_PATH, new Handler(new Controller()));
        Server server = new Server(handlers);
        server.start();

    }
}
