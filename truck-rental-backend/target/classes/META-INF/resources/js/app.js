const API_BASE_URL = '/api';

// Navigation Logic
document.querySelectorAll('.nav-btn').forEach(btn => {
    btn.addEventListener('click', (e) => {
        document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
        e.target.classList.add('active');
        
        document.querySelectorAll('.view').forEach(v => v.classList.remove('active'));
        const targetView = document.getElementById(e.target.getAttribute('data-target'));
        targetView.classList.add('active');
        
        // Refresh dynamically
        if (e.target.getAttribute('data-target') === 'clientes-view') loadClientes();
        if (e.target.getAttribute('data-target') === 'camiones-view') loadCamiones();
        if (e.target.getAttribute('data-target') === 'reservas-view') loadReservas();
    });
});

// Init load
loadClientes();
loadCamiones();
loadReservas();

// Modal Handlers
function openModal(modalId) {
    document.getElementById(modalId).classList.add('active');
    
    // reset titles if opening for creation
    if(modalId === 'clienteModal') {
        if(!document.getElementById('clienteId').value) {
            document.getElementById('clienteModalTitle').innerText = 'Nuevo Cliente';
        }
    }
    if(modalId === 'camionModal') {
        if(!document.getElementById('camionId').value) {
            document.getElementById('camionModalTitle').innerText = 'Nuevo Camión';
        }
    }
}

function closeModal(modalId) {
    document.getElementById(modalId).classList.remove('active');
    // Clear forms after closing
    if(modalId === 'clienteModal') {
        document.getElementById('clienteForm').reset();
        document.getElementById('clienteId').value = '';
    }
    if(modalId === 'camionModal') {
        document.getElementById('camionForm').reset();
        document.getElementById('camionId').value = '';
    }
    if(modalId === 'reservaModal') {
        document.getElementById('reservaForm').reset();
    }
}

// ---------------- CLIENTES ---------------- //
async function loadClientes() {
    try {
        const response = await fetch(`${API_BASE_URL}/clientes`);
        if (!response.ok) throw new Error('Network response was not ok');
        const data = await response.json();
        
        const tbody = document.querySelector('#clientes-table tbody');
        tbody.innerHTML = '';
        data.forEach(cliente => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${cliente.id}</td>
                <td>${cliente.nombre}</td>
                <td>${cliente.apellido}</td>
                <td>${cliente.telefono}</td>
                <td>${cliente.direccion}</td>
                <td class="action-btns">
                    <button class="btn btn-secondary" onclick="editCliente(${cliente.id}, '${cliente.nombre}', '${cliente.apellido}', '${cliente.telefono}', '${cliente.direccion}')">Editar</button>
                    <button class="btn btn-danger" onclick="deleteCliente(${cliente.id})">Eliminar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Error fetching clientes:', error);
    }
}

document.getElementById('clienteForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('clienteId').value;
    const body = {
        nombre: document.getElementById('clienteNombre').value,
        apellido: document.getElementById('clienteApellido').value,
        telefono: document.getElementById('clienteTelefono').value,
        direccion: document.getElementById('clienteDireccion').value
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_BASE_URL}/clientes/${id}` : `${API_BASE_URL}/clientes`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.message || 'Error saving cliente');
        }
        closeModal('clienteModal');
        loadClientes(); // Reload
    } catch (error) {
        alert(error.message);
    }
});

function editCliente(id, nombre, apellido, tel, dir) {
    document.getElementById('clienteModalTitle').innerText = 'Editar Cliente';
    document.getElementById('clienteId').value = id;
    document.getElementById('clienteNombre').value = nombre;
    document.getElementById('clienteApellido').value = apellido;
    document.getElementById('clienteTelefono').value = tel;
    document.getElementById('clienteDireccion').value = dir;
    openModal('clienteModal');
}

async function deleteCliente(id) {
    if(!confirm('¿Estás seguro de que deseas eliminar este cliente?')) return;
    try {
        const response = await fetch(`${API_BASE_URL}/clientes/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Error al eliminar');
        loadClientes();
    } catch (error) {
        alert('Hubo un error al eliminar el cliente');
    }
}

// ---------------- CAMIONES ---------------- //
async function loadCamiones() {
    try {
        const response = await fetch(`${API_BASE_URL}/camiones`);
        if (!response.ok) throw new Error('Error fetching camiones');
        const data = await response.json();
        
        const tbody = document.querySelector('#camiones-table tbody');
        tbody.innerHTML = '';
        data.forEach(camion => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${camion.id}</td>
                <td>${camion.nombre}</td>
                <td>${camion.capacidadCargaKg.toFixed(2)}</td>
                <td>${camion.estado ? '<span style="color:var(--primary);font-weight:bold;">Activo</span>' : '<span style="color:var(--danger);font-weight:bold;">Inactivo</span>'}</td>
                <td class="action-btns">
                    <button class="btn btn-secondary" onclick="editCamion(${camion.id}, '${camion.nombre}', ${camion.capacidadCargaKg})">Editar</button>
                    ${camion.estado ? `<button class="btn btn-danger" onclick="deleteCamion(${camion.id})">Desactivar</button>` : ''}
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error(error);
    }
}

document.getElementById('camionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('camionId').value;
    const body = {
        nombre: document.getElementById('camionNombre').value,
        capacidadCargaKg: parseFloat(document.getElementById('camionCapacidad').value)
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_BASE_URL}/camiones/${id}` : `${API_BASE_URL}/camiones`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.message || 'Error saving camion');
        }
        closeModal('camionModal');
        loadCamiones();
    } catch (error) {
        alert(error.message);
    }
});

function editCamion(id, nombre, capac) {
    document.getElementById('camionModalTitle').innerText = 'Editar Camión';
    document.getElementById('camionId').value = id;
    document.getElementById('camionNombre').value = nombre;
    document.getElementById('camionCapacidad').value = capac;
    openModal('camionModal');
}

async function deleteCamion(id) {
    if(!confirm('¿Estás seguro de que deseas desactivar este camión?')) return;
    try {
        const response = await fetch(`${API_BASE_URL}/camiones/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Error al eliminar');
        loadCamiones();
    } catch (error) {
        alert('Hubo un error al eliminar el camión');
    }
}

// ---------------- RESERVAS ---------------- //
function formatDate(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleString('es-ES');
}

async function loadReservas() {
    try {
        const response = await fetch(`${API_BASE_URL}/reservas`);
        if (!response.ok) throw new Error('Error fetching reservas');
        const data = await response.json();
        
        const tbody = document.querySelector('#reservas-table tbody');
        tbody.innerHTML = '';
        data.forEach(res => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${res.id}</td>
                <td># ${res.idCliente}</td>
                <td># ${res.idCamion}</td>
                <td>${formatDate(res.fechaDesde)}</td>
                <td>${formatDate(res.fechaHasta)}</td>
                <td>${res.lugarDesde} &rarr; ${res.lugarHasta}</td>
                <td>${res.pesoCargaKg.toFixed(2)}</td>
                <td>${res.estado ? 'Activa' : 'Finalizada'}</td>
                <td class="action-btns">
                    <button class="btn btn-danger" onclick="deleteReserva(${res.id})">Eliminar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error(error);
    }
}

document.getElementById('reservaForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const idCamionValue = document.getElementById('reservaCamion').value;
    
    // API expects format 2026-04-10 08:00:00 (we send string based on DTO)
    const body = {
        idCliente: parseInt(document.getElementById('reservaCliente').value),
        idCamion: idCamionValue ? parseInt(idCamionValue) : null,
        fechaDesde: document.getElementById('reservaFechaDesde').value,
        fechaHasta: document.getElementById('reservaFechaHasta').value,
        lugarDesde: document.getElementById('reservaLugarDesde').value,
        lugarHasta: document.getElementById('reservaLugarHasta').value,
        pesoCargaKg: parseFloat(document.getElementById('reservaPeso').value)
    };

    try {
        const response = await fetch(`${API_BASE_URL}/reservas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });
        
        if (!response.ok) {
            const err = await response.json();
            throw new Error(err.message || 'Error al guardar la reserva. Verifica formato de fechas.');
        }
        
        closeModal('reservaModal');
        loadReservas();
        loadCamiones(); // Update trucks list
    } catch (error) {
        alert(error.message);
    }
});

async function deleteReserva(id) {
    if(!confirm('¿Seguro que deseas eliminar esta reserva? El camión será liberado.')) return;
    try {
        const response = await fetch(`${API_BASE_URL}/reservas/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Error al eliminar');
        loadReservas();
        loadCamiones();
    } catch (error) {
        alert('Hubo un error al eliminar la reserva');
    }
}
