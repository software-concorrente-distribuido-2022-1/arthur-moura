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

            string? idade;
            string? tempoServico;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                idade = entrada.ReadLine();
                tempoServico = entrada.ReadLine();

                int id = int.Parse(idade!);
                int temp = int.Parse(tempoServico!);

                if ((id >= 65 || temp >= 30) || (id >= 60 && temp >= 25))
                {
                    saida.WriteLine("Pode se aposentar.");
                }

                else saida.Write("Nao pode se aposentar");

                //else
                //{
                //    erroRqt = true;
                //    msgErro = MsgBadRqt;
                //    saida.WriteLine("Erro ...!");
                //}

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
