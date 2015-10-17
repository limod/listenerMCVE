MCVE regarding this issue: http://stackoverflow.com/posts/33171442/edit

Problem:
On index.xhtml click the rendered "prime" Buttons => actionListener will not be executed.

Solution:
Rendering method of Row.java (Line 77) is not using omnifaces.

After changing the rendering method to following (Row.java Line 67), it worked as expected:
  public void render(UIComponent parent) {
        UIComponent composite = Components.includeCompositeComponent(parent, "qcockpit", Row.RESOURCENAME,"row-" +this.getId());
         for (KpiPast item : this.elements) {
            item.render(composite);
        }
    }
