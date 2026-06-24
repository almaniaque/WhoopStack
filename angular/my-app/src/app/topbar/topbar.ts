import { Component, signal } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';


@Component({
  selector: 'app-topbar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './topbar.html',
  styleUrl: './topbar.css',
})
export class Topbar {
  isOpen = signal(true);

  toggle() {
    this.isOpen.set(!this.isOpen());
  }

  close() {
    this.isOpen.set(false);
  }
}