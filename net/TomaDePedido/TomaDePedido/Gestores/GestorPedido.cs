namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;

    /// <summary>
    /// Gestiona la comunicación entre la interfaz de pedidos y los modulos externos de los cuales necesita información
    /// </summary>
    public class GestorPedido : IGestorPedido
    {
        private IGestorCocina Cocina;
        private IGestorStock Stock;
        private IGestorFacturacion Facturacion;

        public double ObtenerSaldoMesa(int codigo)
        {
            return Facturacion.ObtenerSaldoMesa(codigo);
        }

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

        public void AbrirMesa(int codigoMesa)
        {
            this.Facturacion.AbrirMesa(codigoMesa);
        }

        public void CerrarMesa(int codigoMesa)
        {
            this.Facturacion.CerrarMesa(codigoMesa);
        }

        public Enums.EstadoMesa ObtenerEstadoMesa(int codigoMesa)
        {
            var estado = this.Facturacion.ObtenerEstadoMesa(codigoMesa);
            return (Enums.EstadoMesa)estado;
        }

        public void Alertar(int codigo)
        {

        }
    }
}
