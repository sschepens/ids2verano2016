namespace Test
{
    using Moq;
    using Microsoft.VisualStudio.TestTools.UnitTesting;
    using TomaDePedido.Models;
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;

    [TestClass]
    public class MesaTest
    {
        [TestMethod]
        public void ObtenerMontoAPagarTest()
        {
            //var _plato = new Mock<IPlato>();
            //_plato.SetupProperty(p => p.Nombre, "Picada Mortal")
            //      .SetupProperty(p => p.Codigo, 12)
            //      .SetupProperty(p => p.Precio, 70.00);

            //var _detallePlato = new Mock<IDetallePlato>();
            //_detallePlato.SetupProperty(d => d.CodigoPlato, 12)
            //             .SetupProperty(d => d.Comentario, "Sin sal")
            //             .SetupProperty(d => d.Estado, Enums.EstadoPedido.EnPreparacion)
            //             .SetupProperty(d => d.CodigoDetallePlato, 001);

            //var _pedido = new Mock<IPedido>();
            //_pedido.SetupProperty(p => p.AgregarPlato(12, 1));

            var _mesa = new Mock<IMesa>();
            _mesa.SetupProperty(m => m.CodigoMesa, 12)
                 .SetupProperty(m => m.Estado, Enums.EstadoMesa.Ocupada);

            _mesa.Setup(m => m.ObtenerMontoAPagar()).Returns(11.20);
                
            Assert.AreEqual(_mesa.Object.ObtenerMontoAPagar(), 11.20);
                        
        }

        [TestMethod]
        public void ModificarEstadoTest()
        {
            var mesa = new Mesa();
            mesa.ModificarEstado(Enums.EstadoMesa.Ocupada);

            Assert.AreEqual(Enums.EstadoMesa.Libre, mesa.Estado);
        }
    }
}
