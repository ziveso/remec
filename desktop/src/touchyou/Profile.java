package touchyou;

import java.util.ArrayList;
import java.util.List;

/**
 * Profile class contains its name and List of Command objects.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class Profile {
    private String name;
    private List<Command> commands;

    /**
     * Constructs a Profile with profile name.
     * 
     * @param name
     *            is the name of this profile
     */
    public Profile(String name) {
	this.name = name;
	commands = new ArrayList<>();
    }

    /**
     * Returns the name of this profile.
     */
    public String getName() {
	return name;
    }

    /**
     * Set the name of this profile.
     * 
     * @param name
     *            is the name to be set to
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Returns List of Command in this profile.
     * 
     * @return List of commands
     */
    public List<Command> getCommands() {
	return commands;
    }

    /**
     * Add a command to this profile.
     * 
     * @param command
     *            is the command to be added
     * @return true if added successfully, false otherwise
     */
    public boolean addCommand(Command command) {
	return this.commands.add(command);
    }

    /**
     * Remove given Command from the command list.
     * 
     * @param command
     *            is the command to be removed
     * @return true if removed successfully, false otherwise
     */
    public boolean removeCommand(Command command) {
	return this.commands.remove(command);
    }

    /**
     * Remove a Command with given command's id.
     * 
     * @param id
     *            is the command's id
     * @return true if removed successfully, false otherwise
     */
    public boolean removeCommand(int id) {
	return this.commands.removeIf((command) -> command.getId() == id);
    }

    /**
     * Return a command with a given ID, null if not exist.
     * 
     * @param id
     *            is the ID of the command
     * @return command with a specified id
     */
    public Command getCommand(int id) {
	for (Command command : commands) {
	    if (command.getId() == id)
		return command;
	}
	return null;
    }

}
