//package com.creceperu.app.model;
//import javax.persistence.*;
//import lombok.Data;
//import org.hibernate.mapping.List;
//
//
//@Data
//@Entity
//@Table(name = "TipoBanco")
//public class TipoBanco {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idTipoBanco")
//    private Long idTipoBanco;
//
//    @Column(name = "nombre_tipo_banco")
//    private String nombreTipoBanco;
//
//    @OneToMany(mappedBy = "TipoBanco")
//    private CuentaBancariaa cuentaBancaria;
//}
package com.creceperu.app.model;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.mapping.List;

@Entity
@Data
@Table(name = "tipo_Banco")
public class TipoBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoBanco")
    private Long idTipoBanco;

    @Column(name = "nombreTipoBanco")
    private String nombreTipoBanco;

//    @OneToMany(mappedBy = "TipoBanco")
//    private CuentaBancariaa cuentaBancaria;


}
