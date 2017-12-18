/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devionn.base.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/** 
 *
 * @author anderson
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.devionn.base.service.UsuarioWs.class);
        resources.add(br.com.devionn.base.service.EmpresaWs.class); 
        resources.add(br.com.devionn.base.service.EmailUsuarioWs.class); 
        resources.add(br.com.devionn.base.service.ModuloWs.class);
        resources.add(br.com.devionn.base.service.MenuWs.class);
        resources.add(br.com.devionn.base.service.TelaWs.class);
        resources.add(br.com.devionn.base.service.GrupoUsuarioWs.class);
        resources.add(br.com.devionn.base.service.FiltroWs.class);
        resources.add(br.com.devionn.base.service.IdiomaWs.class);
        resources.add(br.com.devionn.base.service.EstadoWs.class);
        resources.add(br.com.devionn.base.service.CidadeWs.class);
        resources.add(br.com.devionn.base.service.PaisWs.class);
        resources.add(br.com.devionn.base.service.DireitoGrupoWs.class);
        resources.add(br.com.devionn.base.service.TenantWs.class);
        resources.add(br.com.devionn.base.service.TranslateWs.class);
        resources.add(br.com.devionn.base.service.ContaEmailWs.class);
        resources.add(br.com.devionn.base.service.SistemaWs.class);
        resources.add(br.com.devionn.base.service.SistemaWs.class);
         
        resources.add(br.com.devionn.base.service.filtros.NewCrossOriginResourceSharingFilter.class);
    } 
}
