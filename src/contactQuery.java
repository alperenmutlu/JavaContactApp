
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class contactQuery {
    
    
    public void insertContact(contact cont)
    {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO `mycontact`( `fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic`, `userid`) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getUid());
            
             if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "New Contact Added");  
                
                }else{
                     JOptionPane.showMessageDialog(null, "Something Wrong"); 
                     
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
      public void updateContact(contact cont, boolean  withImage)
    {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        
        if(withImage == true) // if the user want to update the contact profile picture to
        {
            updateQuery = "UPDATE `mycontact` SET `fname`=?,`lname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ?,`pic`= ? WHERE `id` = ?";
            
               try {
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getCid());
            
             if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Contact Data Edited");  
                
                }else{
                     JOptionPane.showMessageDialog(null, "Something Wrong"); 
                     
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        }
        
        else{// the user want to keep the same image [remove the image from the update]
            updateQuery = "UPDATE `mycontact` SET `fname`=?,`lname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ? WHERE `id` = ?";
            
               try {
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setInt(7, cont.getCid());
            
             if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Contact Data Edited");  
                
                }else{
                     JOptionPane.showMessageDialog(null, "Something Wrong"); 
                     
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }
      
       public void deleteContact(int cid)
    {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("DELETE FROM `mycontact` WHERE `id`= ?");
            ps.setInt(1,cid);
            
            
             if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Contact Deleted");  
                
                }else{
                     JOptionPane.showMessageDialog(null, "Something Wrong"); 
                     
                }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }   
    
      
    
      
    
    //create a list of contact
    public ArrayList<contact> contactList(int userId){
        
        ArrayList<contact> clist = new ArrayList<contact>();
        
        Connection con = myConnection.getConnection();
        Statement st ;
        ResultSet rs;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT `id`, `fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic` FROM `mycontact` WHERE userid =  " + userId);  
            
            contact ct;
            
            while(rs.next()){
                  ct = new contact(rs.getInt("id"),
                                   rs.getString("fname"),
                                   rs.getString("lname"),
                                   rs.getString("groupc"),
                                   rs.getString("phone"), 
                                   rs.getString("email"),
                                   rs.getString("address"),
                                   rs.getBytes("pic"),
                                   userId);
                               
                
                clist.add(ct);
                       
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clist;
        
    }
    
}
