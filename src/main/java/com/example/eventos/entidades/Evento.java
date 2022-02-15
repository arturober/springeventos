package com.example.eventos.entidades;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.example.eventos.entidades.proyecciones.EventoSinAsistentes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    @NotBlank(message = "la descripción es obligatoria")
    @Size(min = 8, max = 2000, message = "La descripción tiene que tener al menos 8 caracteres")
    private String descripcion;
    @Positive(message = "El precio debe ser mayor que 0")
    private double precio;
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    @NotBlank
    private String imagen;

    @ManyToMany(mappedBy = "eventos")
    List<Usuario> asistentes;

    public Evento(EventoSinAsistentes e) {
        this.id = e.getId();
        this.descripcion = e.getDescripcion();
        this.fecha = e.getFecha();
        this.imagen = e.getImagen();
        this.precio = e.getPrecio();
        this.titulo = e.getTitulo();
    }

    public void addUsuario(Usuario u) {
        asistentes.add(u);
        u.eventos.add(this);
    }

    public void removeUsuario(Usuario usuario) {
        asistentes.remove(usuario);
        usuario.eventos.remove(this);
    }
}
