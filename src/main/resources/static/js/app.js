const API_URL = "http://localhost:8080/libros";

const tabla = document.getElementById("tablaLibros");
const form = document.getElementById("libroForm");

form.addEventListener("submit", guardarLibro);

// =====================
// LISTAR
// =====================
function listarLibros() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            tabla.innerHTML = "";
            data.forEach(libro => {
                tabla.innerHTML += `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.fechaPublicacion}</td>
                        <td>${libro.stock}</td>

                        <td>
                            <button onclick="editarLibro(${libro.id})">✏️</button>
                            <button onclick="eliminarLibro(${libro.id})">🗑️</button>
                        </td>
                    </tr>
                `;
            });
        });
}

// =====================
// CREAR
// =====================
function guardarLibro(e) {
    e.preventDefault();

    const id = document.getElementById("id").value;

    const libro = {
        titulo: document.getElementById("titulo").value,
        autor: document.getElementById("autor").value,
        fechaPublicacion: document.getElementById("fechaPublicacion").value,
        stock: parseInt(document.getElementById("stock").value)
    };

    // 👉 POST: crear
    if (!id) {
        fetch(`${API_URL}/crear`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify([libro]) // 👈 LISTA
        })
            .then(() => {
                form.reset();
                listarLibros();
            });
    }
    // 👉 PATCH: actualizar
    else {
        fetch(`${API_URL}/actualizar/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(libro)
        })
            .then(() => {
                form.reset();
                listarLibros();
            });
    }
}


// =====================
// EDITAR
// =====================
function editarLibro(id) {
    fetch(`${API_URL}/${id}`)
        .then(res => res.json())
        .then(libro => {
            document.getElementById("id").value = libro.id;
            document.getElementById("titulo").value = libro.titulo;
            document.getElementById("autor").value = libro.autor;
            document.getElementById("fechaPublicacion").value = libro.fechaPublicacion;
            document.getElementById("stock").value = libro.stock;
        });
}


// =====================
// ELIMINAR
// =====================
function eliminarLibro(id) {
    if (!confirm("¿Eliminar este libro?")) return;

    fetch(`${API_URL}/eliminar/${id}`, { method: "DELETE" })
        .then(() => listarLibros());
}


// Inicial
listarLibros();
