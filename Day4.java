import java.io.*;
import java.util.*;

class NoteManager {
    private static final String FILE_NAME = "notes.txt";

    public void addNote(String text) {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(text + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }

    public List<String> getNotes() {
        List<String> notes = new ArrayList<>();
        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                notes.add(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No notes found.");
        }
        return notes;
    }

    public List<String> searchNotes(String keyword) {
        List<String> result = new ArrayList<>();
        List<String> allNotes = getNotes();

        for (String note : allNotes) {
            if (note.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(note);
            }
        }
        return result;
    }

    public void deleteNote(int index) {
        List<String> notes = getNotes();
        if (index < 1 || index > notes.size()) {
            System.out.println("Invalid note number.");
            return;
        }
        notes.remove(index - 1);

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME));
            for (String n : notes) {
                pw.println(n);
            }
            pw.close();
            System.out.println("Note deleted.");
        } catch (Exception e) {
            System.out.println("Error deleting note.");
        }
    }
}

public class NotesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NoteManager nm = new NoteManager();

        while (true) {
            System.out.println("\n======= NOTES APP =======");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Search Notes");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter note: ");
                String note = sc.nextLine();
                nm.addNote(note);
                System.out.println("Saved!");
            } 
            else if (choice == 2) {
                List<String> notes = nm.getNotes();
                if (notes.isEmpty()) {
                    System.out.println("No notes available.");
                } else {
                    System.out.println("\n--- All Notes ---");
                    for (int i = 0; i < notes.size(); i++) {
                        System.out.println((i + 1) + ". " + notes.get(i));
                    }
                }
            } 
            else if (choice == 3) {
                System.out.print("Enter keyword to search: ");
                String key = sc.nextLine();
                List<String> result = nm.searchNotes(key);

                if (result.isEmpty()) {
                    System.out.println("No matching notes found.");
                } else {
                    System.out.println("\n--- Search Results ---");
                    for (String r : result) {
                        System.out.println("- " + r);
                    }
                }
            } 
            else if (choice == 4) {
                List<String> notes = nm.getNotes();
                if (notes.isEmpty()) {
                    System.out.println("No notes to delete.");
                } else {
                    System.out.println("\n--- Notes ---");
                    for (int i = 0; i < notes.size(); i++) {
                        System.out.println((i + 1) + ". " + notes.get(i));
                    }

                    System.out.print("Enter note number to delete: ");
                    int num = sc.nextInt();
                    nm.deleteNote(num);
                }
            } 
            else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } 
            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
