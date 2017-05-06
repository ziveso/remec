package touchyou;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String name;
    private List<Command> commands;

    public Profile(String name) {
	this.name = name;
	commands = new ArrayList<>();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Command> getCommands() {
	return commands;
    }

    public boolean addCommand(Command command) {
	return this.commands.add(command);
    }

    public boolean removeCommand(Command command) {
	return this.commands.remove(command);
    }

    public boolean removeCommand(int id) {
	return this.commands.removeIf((command) -> command.getId() == id);
    }

}
