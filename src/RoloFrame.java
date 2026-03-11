import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RoloFrame extends JFrame {
    private static ArrayList<RoloContact> cla = new ArrayList<>();
    private static boolean r;
    JList cl = new JList(cla.toArray());
    JScrollPane sp = new JScrollPane(cl, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    JLabel f = new JLabel("First Name:");
    JLabel l = new JLabel("Last Name:");
    JLabel pn = new JLabel("Phone Number:");
    JLabel a = new JLabel("Address:");
    JTextField fi = new JTextField();
    JTextField li = new JTextField();
    JTextField pni = new JTextField();
    JTextField ai = new JTextField();
    JButton s = new JButton("Save");
    JButton n = new JButton("New");
    JButton sc = new JButton("Save Changes");
    JButton dc = new JButton("Delete Contact");
    public RoloFrame(String frameName, int panelWidth, int panelHeight) {
        super(frameName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        r = true;
        setLayout(null);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        sp.setBounds(10, 50, 400, 600);
        cl.addListSelectionListener(e -> hn());
        add(sp);
        f.setFont(new Font("Calibre", Font.BOLD, 15));
        f.setBounds(420, 70, 200, 15);
        add(f);
        l.setFont(new Font("Calibre", Font.BOLD, 15));
        l.setBounds(420, 170, 200, 15);
        add(l);
        pn.setFont(new Font("Calibre", Font.BOLD, 15));
        pn.setBounds(420, 270, 200, 15);
        add(pn);
        a.setFont(new Font("Calibre", Font.BOLD, 15));
        a.setBounds(420, 370, 200, 15);
        add(a);
        fi.setFont(new Font("Calibre", Font.ITALIC, 12));
        fi.setBounds(620, 70, 250, 20);
        add(fi);
        li.setFont(new Font("Calibre", Font.ITALIC, 12));
        li.setBounds(620, 170, 250, 20);
        add(li);
        pni.setFont(new Font("Calibre", Font.ITALIC, 12));
        pni.setBounds(620, 270, 250, 20);
        add(pni);
        ai.setFont(new Font("Calibre", Font.ITALIC, 12));
        ai.setBounds(620, 370, 250, 20);
        add(ai);
        s.setFont(new Font("Calibre", Font.BOLD, 15));
        s.setBounds(540, 420, 75, 30);
        s.addActionListener(e -> save());
        s.setVisible(true);
        add(s);
        n.setFont(new Font("Calibre", Font.BOLD, 15));
        n.setBounds(665, 420, 75, 30);
        n.addActionListener(e -> newC());
        n.setVisible(true);
        add(n);
        sc.setFont(new Font("Calibre", Font.BOLD, 15));
        sc.setBounds(540, 500, 200, 30);
        sc.addActionListener(e -> saveC());
        sc.setVisible(false);
        add(sc);
        dc.setFont(new Font("Calibre", Font.BOLD, 15));
        dc.setBounds(540, 560, 200, 30);
        dc.setVisible(false);
        dc.addActionListener(e -> deleteC());
        add(dc);
        pack();
        setVisible(true);
    }
    public void save() {
        if(fi.getText().equals("") || li.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Your contact must have a first and last name.");
        }
        else {
            RoloContact c = new RoloContact(li.getText(), fi.getText(), pni.getText(), ai.getText());
            cla.add(c);
            setAlphabetical();
            write(true);
            cl.setListData(cla.toArray());
            fi.setText("");
            li.setText("");
            pni.setText("");
            ai.setText("");
        }
    }
    public void newC() {
        fi.setText("");
        li.setText("");
        pni.setText("");
        ai.setText("");
        cla.clear();
        cl.clearSelection();
        write(false);
        cl.setListData(cla.toArray());
    }
    public void saveC() {
        cla.get(cl.getSelectedIndex()).setl(li.getText());
        cla.get(cl.getSelectedIndex()).setf(fi.getText());
        cla.get(cl.getSelectedIndex()).setp(pni.getText());
        cla.get(cl.getSelectedIndex()).seta(ai.getText());
        cl.clearSelection();
        fi.setText("");
        li.setText("");
        pni.setText("");
        ai.setText("");
        sc.setVisible(false);
        dc.setVisible(false);
        s.setVisible(true);
        n.setVisible(true);
        setAlphabetical();
        cl.setListData(cla.toArray());
        write(true);
        r = true;
    }
    public void deleteC() {
        if(cla.size() == 1)  {
            cl.clearSelection();
            cla.clear();
            cl.clearSelection();
            fi.setText("");
            li.setText("");
            pni.setText("");
            ai.setText("");
            sc.setVisible(false);
            dc.setVisible(false);
            s.setVisible(true);
            n.setVisible(true);
            setAlphabetical();
            cl.setListData(cla.toArray());
            write(true);
            r = true;
        }
        else {
            cla.remove(cl.getSelectedIndex());
            fi.setText("");
            li.setText("");
            pni.setText("");
            ai.setText("");
            cl.clearSelection();
            sc.setVisible(false);
            dc.setVisible(false);
            s.setVisible(true);
            n.setVisible(true);
            setAlphabetical();
            cl.setListData(cla.toArray());
            write(true);
            r = true;
        }
    }
    public void hn() {
        if(r) {
            s.setVisible(false);
            n.setVisible(false);
            sc.setVisible(true);
            dc.setVisible(true);
            r = false;
            fi.setText(cla.get(cl.getSelectedIndex()).getf());
            li.setText(cla.get(cl.getSelectedIndex()).getl());
            pni.setText(cla.get(cl.getSelectedIndex()).getp());
            ai.setText(cla.get(cl.getSelectedIndex()).geta());
        }
    }
    public void setAlphabetical() {
        ArrayList<String> arr = new ArrayList<>();
        for(int i = 0;i < cla.size();i++) {
            arr.add(cla.get(i).getl());
        }
        Collections.sort(arr);
        ArrayList<RoloContact> a = new ArrayList<>();
        for(int i = 0;i < arr.size();i++) {
            for (int k = 0; k < arr.size(); k++) {
                if (cla.get(k).getl().equals(arr.get(i))) {
                    a.add(cla.get(k));
                    k = arr.size();
                }
            }
        }
    }
    public void write(boolean b) {
        if(b) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", false))) {
                for (int i = 0; i < cla.size(); i++) {
                    writer.write(cla.get(i).toString() + " Phone Number: " + cla.get(i).getp() + " Adress: " + cla.get(i).geta());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", false))) {
                for (int i = 0; i < cla.size(); i++) {
                    writer.write("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}