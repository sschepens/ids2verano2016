namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación entre la interfaz de pedidos y los modulos externos de los cuales necesita información
    /// </summary>
    public class GestorPedido : IGestorPedido
    {
        private IGestorCocina Cocina;
        private IGestorStock Stock;
        private IGestorFacturacion Facturacion;

        public GestorPedido()
        {
            this.Cocina = new GestorCocina();
            this.Stock = new GestorStock();
            this.Facturacion = new GestorFacturacion();
        }
        
        public void TomarPedido()
        {
            throw new NotImplementedException();
        }
    }
}
