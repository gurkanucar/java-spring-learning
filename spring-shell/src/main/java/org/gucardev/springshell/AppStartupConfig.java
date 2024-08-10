package org.gucardev.springshell;

import org.gucardev.springshell.util.ShellPrinter;
import org.fusesource.jansi.Ansi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppStartupConfig implements ApplicationContextAware {

    @Autowired
    private ShellPrinter printer;

    // it will clear screen | CommandlineRunner or ApplicationRunner did not work here.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       // clearConsole();
        printCommands();
    }

    public void clearConsole(){
        if (System.console() != null) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else {
            System.out.println("============================================");
        }
    }

    public void printCommands(){
        // print commands
        printer.printTable(Ansi.Color.YELLOW,"League Commands", "league -l: get list");
        printer.printTable(Ansi.Color.RED,"Pharmacy Commands", "pharmacy: Get duty pharmacy");
        printer.printTable("Test Commands", "* private: private command", "print: printTest command", "public: public command");
        printer.printTable(Ansi.Color.GREEN,"Todo Commands", "* todo -c: create new", "* todo -d: delete todo", "* todo -t: toggle todo status", "* todo -l: get list");
    }

}
