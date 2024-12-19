
package com.library.service;

import java.util.List;

import com.library.dao.BorrowDAO;
import com.library.model.Borrow;

public class BorrowService {

    private BorrowDAO borrowDAO;

    // Constructeur avec BorrowDAO
    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }

    // Méthode pour emprunter un livre
    public String borrowBook(Borrow borrow) {
        // Sauvegarde de l'emprunt dans la base de données
        return borrowDAO.addBorrow(borrow);
    }

    // Afficher les emprunts (méthode fictive, à adapter)
    public List<Borrow> displayBorrows() {
        List<Borrow> borrows = borrowDAO.getAllBorrows();
        if(borrows != null)
        {
            for (Borrow borrow : borrows) {
                System.out.println(borrow.getId());
            }
        }
        return borrows;
    }

    public Borrow getBorrowById(int id) {
        return borrowDAO.getById(id);
    }

    public String deleteBorrow(int id) {
        return borrowDAO.deleteBorrow(id);
    }

    public String deleteAllBorrows() {
        return borrowDAO.deleteAll();
    }

    public String updateBorrow(Borrow borrow) {
        return borrowDAO.save(borrow);
    }
}