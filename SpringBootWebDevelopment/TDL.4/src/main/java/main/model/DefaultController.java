package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable) {
            tasks.add(task);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskCount", tasks.size());
        return "index";
    }
}
