//-----------------------------------------------------------------------
// <copyright file="Menu.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Models
{
    using System.Collections.Generic;
    using System.Linq;

    /// <summary>
    /// Inicializa una nueva instancia de la clase Menu
    /// </summary>
    public class Menu
    {
        public Menu()
        {
            this.Platos = new List<Plato>();
            this.Cervezas = new List<Cerveza>();
        }

        public Menu(List<Plato> platos, List<Cerveza> cervezas)
        {
            this.Platos = platos != null ? platos : new List<Plato>();
            this.Cervezas = cervezas != null ? cervezas : new List<Cerveza>();
        }

        public List<Plato> Platos { get; }

        public List<Cerveza> Cervezas { get; }

        public Plato ObtenerPlato(int codigo)
        {
            return this.Platos.Where(p => p.Codigo == codigo).FirstOrDefault();
        }

        public Cerveza ObtenerCerveza(int codigo)
        {
            return this.Cervezas.Where(c => c.Codigo == codigo).FirstOrDefault();
        }
    }
}
