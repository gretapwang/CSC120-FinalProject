// EnergySystem.java - Main class for the energy management system
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EnergySystem {
    private int energy;   //energy 
    private boolean isRunning;
    private Scanner scanner;
    private Map<String, Command> commands;
    private List<Weapon> Weapons; //axe,sword,knife,frypan
    private Weapon equippedWeapon;

    public EnergySystem() {
        this.energy = 100;   l//energylevel = max 100
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.commands = new HashMap<>();
        this.Weapons = new ArrayList<>();
        this.equippedWeapon = null;
        initializeWeapons();    //for the weapon part
        initializeCommands();    //commands like go check help food water
        //we use comments so we can understand the purpose of code 
    }

    private void initializeWeapons(){
        //create some default weapons

        weapons.add(new weapon("sword", 20,5,10));
        weapons.add(new weapon("Axe" ,30,8,8))
        weapons.add(new weapon("knife" 10,8,8))
        weapons.add(new weapon("frypan "5,3,8))
    }
    // Command interface for all actions
    private interface Command {
        void execute();
        String getDescription();
    }

    private void initializeCommands() {
        // Add all commands with their functionality
        commands.put("energy", new Command() {
            @Override
            public void execute() {
                System.out.println("Current energy: " + energy);
            }
            
            @Override
            public String getDescription() {
                return "Check current energy level";
            }
        });

        commands.put("food", new Command() {
            @Override
            public void execute() {
                energy += 10;
                energy += 50; //add energy levels
                System.out.println("Ate food. Energy +10. Current: " + energy);
                system.out.println("energy is sufficient +50" +energy);
            }
            
            @Override
            public String getDescription() {
                return "Eat food to gain energy";
            }
        });

        commands.put("water", new Command() {
            @Override
            public void execute() {
                energy += 5; //add commands level according to your need
                System.out.println("Drank water. Energy +5. Current: " + energy);
            }
            
            @Override
            public String getDescription() {
                return "Drink water to gain energy";
            }
        });

        commands.put("kill", new Command() {
            @Override
            public void execute() {

                if(equippedWeapon==Null){
                    System.out.println("you need to equip a weapon first ");
                    return;
                }
                if (equippedWeapon.isBroken()){
                    System.out.println("your equipment"+equippedWeapon.getName()+"is broken repair it first");
                }
                if(energy>= weaponcost){
                    energy -= weaponcost;
                    equippedWeapon.useweapon();
                }
                int gained = 15;
                int weapon bonus equippedWeapon.getDamage()/2;
                int totalgain = basegain+weaponBonus;
                energy += gained;
                System.out.println("Killed monster! Energy +" + gained + ". Current: " + energy);
            }
            
            @Override
            public String getDescription() {
                return "Kill a monster to gain energy";
            }
        });

        commands.put("go", new Command() {
            @Override
            public void execute() {
                int cost = 8;
                if (energy >= cost) {
                    energy -= cost;
                    System.out.println("Moved to new location. Energy -" + cost + ". Current: " + energy);
                } else {
                    System.out.println("Not enough energy to move! Need " + cost + ", have " + energy);
                }
            }
            
            @Override
            public String getDescription() {
                return "Move to a new location (costs energy)";
            }
        });

        commands.put("check", new Command() {
            @Override
            public void execute() {
                if (energy > 50) {   //check energy status 
                    System.out.println("Energy status: Good");
                } else if (energy > 20) {
                    System.out.println("Energy status: Low");
                } else {
                    System.out.println("Energy status: Critical!");
                }
            }
            
            @Override
            public String getDescription() {
                return "Check your energy status";
            }
        });

        commands.put("help", new Command() {
            @Override
            public void execute() {
                System.out.println("\n=== Available Commands ===");
                for (Map.Entry<String, Command> entry : commands.entrySet()) {
                    System.out.printf("%-10s - %s%n", entry.getKey(), entry.getValue().getDescription());
                }
                System.out.println("quit      - Exit the program");
                
            }
            
            @Override
            public String getDescription() {
                return "Show this help menu";
            }
        });
    }

    public void start() {
        System.out.println("=== Energy System Started ===");
        System.out.println("Initial energy: " + energy);
        System.out.println("Type 'help' to see available commands\n");

        while (isRunning) {
            // Check energy level
            if (energy <= 0) {
                System.out.println("You're out of energy! Game over.");
                break;
            }

            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("quit")) {
                isRunning = false;
                System.out.println("Exiting Energy System. Goodbye!");
                continue;
            }

            if (commands.containsKey(input)) {
                commands.get(input).execute();
            } else {
                System.out.println("Unknown command: '" + input + "'. Type 'help' for available commands.");
            }
        }

        scanner.close();
    }

    // Main method to run the program
    public static void main(String[] args) {
        EnergySystem system = new EnergySystem();
        system.start();
    }
} //food,water,energy level,go,check,help
