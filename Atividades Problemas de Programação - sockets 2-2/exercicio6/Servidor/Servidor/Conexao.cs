using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Servidor
{
    public class Conexao : BaseThread
    {
        public readonly string MsgBadRqt = "400 Bad Request";

        Socket SocketCliente;

        public Conexao(Socket aSocketCliente)
        {
            SocketCliente = aSocketCliente;
        }


        public override void RunThread()
        {
            bool erroRqt;
            string? msgErro;

            StreamWriter? saida;
            StreamReader? entrada;

            IPAddress endCliente = ((IPEndPoint)SocketCliente?.RemoteEndPoint)?.Address;

            string? nome;
            string? nivel;
            string? salario;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                nome = entrada.ReadLine();
                nivel = entrada.ReadLine();
                salario = entrada.ReadLine();

                int sb = int.Parse(salario!);

                if (sb >= 5000)
                {
                    Console.WriteLine("boa");
                }

                else
                {
                    erroRqt = true;
                    msgErro = MsgBadRqt;
                    saida.WriteLine("Erro ...!");
                }

                SocketCliente!.Close() ;
                saida.Close();
                entrada.Close();
            }
            catch(IOException e)
            {
                Console.WriteLine("Erro E/S " + e);
            }
        }
    }
}
