namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Models;

    /// <summary>
    /// Gestiona la comunicación con ese Blanco legal que maneja las responsabilidades que no se definieron
    /// </summary>
    public class GestorFantasma : IGestorFantasma
    {
        public List<Cerveza> ObtenerCervezas()
        {
            throw new NotImplementedException();
        }

        public List<Plato> ObtenerPlatos()
        {
            throw new NotImplementedException();
        }
    }
}
