package ups.edu.ec.controller;

/**
 * 
 * @author grupo_3
 */
import ups.edu.ec.model.ICommand;

public class ReproductorInvoker {
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void execute() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No hay comando asignado.");
        }
    }
}